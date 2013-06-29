(ns beatstream.config)

(def db {:classname   "org.h2.Driver"
         :subprotocol "h2"
         :subname     "resources/db/korma.db"})
