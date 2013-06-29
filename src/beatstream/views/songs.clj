(ns beatstream.views.songs
  (:use ring.util.response))

(defn update-medialibrary []
  (str "get music_path, look for files with certain extension, read ID3, add file to database with ID3 info"))

(defn all []
  (response []))

(defn refresh []
  (update-medialibrary))

(defn play [params]
  "Stream the contents of file found at (:file params)"
  (str (:file params)))
