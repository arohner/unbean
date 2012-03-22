(ns unbean.core
  (:import clojure.lang.Reflector
           java.beans.PropertyDescriptor))

(defn inst-class [cls]
  (Reflector/invokeConstructor cls (into-array [])))

(defn capitalize-first [s]
  (apply str (Character/toUpperCase (first s)) (rest s)))

(defn setter
  "Given a field name, like fooBar, return the name of the setter method, setFooBar"
  [class field]
  ;; I didn't use PropertyDescriptor here, because it exploded randomly
  (format "set%s" (capitalize-first field)))

(defn unbean
  "Takes a map created by (bean), returns an instance of the java class"
  ([m]
     (let [cls (-> m :class)]
       (when-not cls
         (throw (Exception "map doesn't contain :class")))
       (unbean (dissoc m :class) cls)))
  ([m class]
     (let [inst (inst-class class)]
       (doseq [[k v] m]
         (when v
           (Reflector/invokeInstanceMethod inst (setter class (name k)) (into-array [v]))))
       inst)))