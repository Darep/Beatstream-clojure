(ns beatstream.common
  (:require [ring.util.response :as response]))

(defn not-found []
  (response/not-found "Not Found"))