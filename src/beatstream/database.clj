(ns beatstream.database
  (:use korma.db))

(def db (h2 {:db "resources/db/korma.db"}))

(defdb korma-db db)

