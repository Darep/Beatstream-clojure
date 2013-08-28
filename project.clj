(defproject beatstream "0.1.0-SNAPSHOT"
  :description "Beatstream backend written in Clojure (RESTful API)"
  :url "http://www.beatstream.fi/"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [ring/ring-json "0.2.0"]
                 [lib-noir "0.6.4"]
                 [ring/ring-jetty-adapter "1.1.0"]
                 [com.h2database/h2 "1.3.170"]
                 [lobos "1.0.0-beta1"]
                 [korma "0.3.0-RC5"]
                 [sonian/carica "1.0.3"]
                 [org/jaudiotagger "2.0.3"]]
                 ; [liberator "0.9.0"]
  :profiles {:dev {:dependencies [[midje "1.5.1"]
                                  [ring-mock "0.1.5"]]}
             :production {:ring {:open-browser? false
                                 :auto-reload? false
                                 :stacktraces? false}}}
  :main beatstream.app)
