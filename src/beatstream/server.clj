(ns beatstream.server
  (:use compojure.core)
  (:require [beatstream.profile :as profile]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.file :refer [wrap-file]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [noir.util.middleware :refer [wrap-strip-trailing-slash wrap-canonical-host wrap-force-ssl]]))

(def db (h2 {:db "resources/db/korma.db"}))

(defdb korma-db db)

(defroutes api-routes
  (context "/profile" []
    (defroutes profile-routes
      (GET "/" [] (profile/show))
      (GET "/lastfm" [] (profile/lastfm))
      (PUT "/" {body :body} (profile/update body))))
  (context "/songs" []
    (defroutes songs-routes
      (GET "/" [] "all songs")
      (GET "/play" [] "play a song, ?file=<path>")
      (POST "/" [] "refresh media libraryyyyyhhh")))
  ; TODO: playlist routes
  ; (context "/playlists" []
  ;   (defroutes playlists-routes
  ;     (GET "/" [] "list user's playlists")))
  (PUT "now-playing" [] "update now playing")
  (POST "scrobble" [] "scrobble the now playing OR specified song")
  (route/not-found "Not Found"))

(def ^:private root "resources/public")

(defroutes public-routes
  (-> (ANY "*" request common/not-found)
      (wrap-file root)
      wrap-file-info))

; (defn wrap-prod-middleware [routes]
;   (if (System/getenv "LEIN_NO_DEV")
;     (-> routes
;         (wrap-canonical-host (System/getenv "CANONICAL_HOST"))
;         (wrap-force-ssl))
;     routes))

(def handler
  (routes
    (handler/api
      (context "/api/v1" []
        (-> api-routes
          (wrap-json-body)
          (wrap-json-response))))
    (handler/site public-routes)))
