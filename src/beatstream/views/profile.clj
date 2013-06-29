(ns beatstream.views.profile
  (:use ring.util.response))

(defn show []
  (response {:username "dummy"}))

(defn lastfm []
  (response (str "lastfm" "test")))

(defn update [body]
  (println body)
  (response (str "updated")))
