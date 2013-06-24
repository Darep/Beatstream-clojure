(defproject beatstream "0.1.0-SNAPSHOT"
  :description "Beatstream backend written in Clojure (RESTful API)"
  :url "http://www.beatstream.fi/"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [ring/ring-json "0.2.0"]
                 [korma "0.3.0-RC5"]
                 [com.h2database/h2 "1.3.170"]
                 [lib-noir "0.6.4"]]
                 ; [liberator "0.9.0"]
                 ; [ring/ring-jetty-adapter "1.1.0"]]
  :plugins [[lein-ring "0.8.5"]]
  :profiles {:dev {:dependencies [[midje "1.5.1"]
                                  [ring-mock "0.1.5"]]}
             :production {:ring {:open-browser? false
                                 :auto-reload? false
                                 :stacktraces? false}}}
  :ring {:handler beatstream.server/handler}
  :main beatstream.server/handler)
