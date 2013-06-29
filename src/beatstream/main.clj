(ns beatstream.main
  (:require [beatstream.server :as server])
  (:gen-class))

(defn start-server []
  (run-jetty (server/app) {:port 8080 :join? false}))

(defn -main [& args]
  (start-server))
