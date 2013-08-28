(ns beatstream.controllers.songs
  (:use ring.util.response)
  (:require clojure.java.io))

(def music-path "/Users/ajk/Music/")

(defn get-song-files [directory]
  (->> directory
       clojure.java.io/file
       file-seq
       (filter #(.isFile %))
       (map #(.getAbsolutePath %))
       (filter #(re-find #"(?i)\.mp3$" %))))

(defn update-medialibrary []
  (str "TODO: read ID3, add file to database with ID3 info")
  (get-song-files music-path))

(defn all []
  "Return all the media files currently in the DB"
  (response []))

(defn refresh []
  "Find all the media files and add them + their meta data into DB"
  (update-medialibrary))

(defn play [params]
  "Stream the contents of file found at (:file params)"
  (str (:file params)))
