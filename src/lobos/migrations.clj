(ns lobos.migrations
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema
               config helpers)))

(defmigration add-users-table
  (up [] (create
          (tbl :users
            (varchar :username 100 :unique)
            (check :username (> (length :name) 2)))))
  (down [] (drop (table :users))))
