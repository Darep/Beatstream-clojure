(ns beatstream.profile
  (:use ring.util.response))

(defn show []
  (response {:hello "world2"}))

(defn lastfm []
  (response (str "lastfm" "test")))

(defn update [body]
  (println body)
  (response (str "updated")))
