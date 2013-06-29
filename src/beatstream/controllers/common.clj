(ns beatstream.controllers.common
  (:require [ring.util.response :as response]))

(defn not-found []
  (response/not-found "404 Not Found"))
