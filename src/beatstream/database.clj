(ns beatstream.database
  (:use korma.db)
  (:use korma.core)
  (:require [beatstream.config :as beatstream-config])))

(def db beatstream-config/db)

(defdb korma-db db)

(defentity users)
