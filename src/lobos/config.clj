(ns lobos.config
  (:use lobos.connectivity)
  (:require [beatstream.config :as beatstream-config]))

(def db beatstream-config/db)

(open-global db)
