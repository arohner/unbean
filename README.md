## unbean

A library of one function, unbean. This is the inverse of `clojure.core/bean`, it creates java classes from maps created by `bean`.
# Install

```clojure
[[unbean "0.1.0"]]
```

## Usage

```clojure
(-> java-obj (bean) (unbean.core/unbean))
```

## License

Copyright © 2012 Allen Rohner

Distributed under the Eclipse Public License, the same as Clojure.
