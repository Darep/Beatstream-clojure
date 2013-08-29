(ns beatstream.controllers.songs
  (:use ring.util.response)
  (:require clojure.java.io)
  (:import [org.jaudiotagger.audio AudioFileIO]
           [org.jaudiotagger.tag FieldKey]))

(def music-path "/Users/ajk/Music/")

(defn get-song-files [directory]
  (->> directory
       clojure.java.io/file
       file-seq
       (filter #(.isFile %))
       (filter #(re-find #"(?i)\.(mp3|ogg)$" (.getAbsolutePath %)))))

(defn update-medialibrary []
  "TODO: read ID3, add file to database with ID3 info"
  "artist, album, title, album artist, tracknumber, title, duration, year, genre, file path"
  (doseq [f (get-song-files music-path)]
    (println (.getFirst (.getTag (AudioFileIO/read f)) FieldKey/ARTIST))))

(defn all []
  "Return all the media files currently in the DB"
  (update-medialibrary))
  ; (response []))

(defn refresh []
  "Find all the media files and add them + their meta data into DB"
  (update-medialibrary))

(defn play [params]
  "Stream the contents of file found at (:file params)"
  (str (:file params)))
