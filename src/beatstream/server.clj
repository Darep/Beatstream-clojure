(ns beatstream.server
  (:use korma.db)
  (:use compojure.core)
  (:require [beatstream.profile :as profile]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]
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

(defroutes public-routes
  (GET "/" [] "yay, display the app here!"))

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
          (middleware/wrap-json-body)
          (middleware/wrap-json-response))))
    (handler/site public-routes)))
