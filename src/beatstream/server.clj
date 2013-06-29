(ns beatstream.server
  (:use compojure.core)
  (:require [beatstream.controllers.common :as common]
            [beatstream.controllers.profile :as profile]
            [beatstream.controllers.songs :as songs]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.file :refer [wrap-file]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.util.response :as resp]
            [noir.util.middleware :refer [wrap-strip-trailing-slash wrap-canonical-host wrap-force-ssl]]))

(defroutes api-routes
  (context "/profile" []
    (defroutes profile-routes
      (GET "/" [] (profile/show))
      (GET "/lastfm" [] (profile/lastfm))
      (PUT "/" {body :body} (profile/update body))))
  (context "/songs" []
    (defroutes songs-routes
      (GET "/" [] (songs/all))
      (GET "/play" {params :params} (songs/play params))
      (POST "/" [] (songs/refresh))))
  (PUT "now-playing" [] "update now playing")
  (POST "scrobble" [] "scrobble the now playing OR specified song")
  (route/not-found (common/not-found)))

(defroutes public-routes
  (GET "/" [] (resp/resource-response "index.html" {:root "public"}))
  (route/resources "/")
  (route/not-found (common/not-found)))

(def handler
  (routes
    (handler/api
      (context "/api/v1" []
        (-> api-routes
            (wrap-json-body)
            (wrap-json-response))))
    (handler/site public-routes)))
