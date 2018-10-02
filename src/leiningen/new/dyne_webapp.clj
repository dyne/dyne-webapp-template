(ns leiningen.new.dyne-webapp
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files raw-resourcer]]
            [leiningen.core.main :as main]))

(def render (renderer "dyne-webapp"))
(def raw (raw-resourcer "dyne-webapp"))

(defn- src [file]
  (str "src/dyne_webapp/" file ".clj"))
(defn- srcres [file data]
  [(str "src/{{sanitized}}/" file ".clj")
   (render (str "src/dyne_webapp/" file ".clj") data)])
(defn- rawres [file]
  [(str "resources/public/static/" file) (raw (str "resources/public/static/" file))])

(defn dyne-webapp
  "Generate a fresh webapp based on dyne.org's clojure tooling"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' dyne-webapp project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["{{sanitized}}.yaml" (render "dyne_webapp_config.yaml" data)]
             (srcres "handler" data)
             (srcres "ring" data)
             (srcres "config" data)
             (srcres "webpage" data)
             (srcres "session" data)
             (rawres "js/bootstrap.min.js")
             (rawres "js/jquery-3.2.1.min.js")
             (rawres "fonts/fa-regular-400.woff")
             (rawres "css/fa-regular.min.css")
             (rawres "css/json-html.css")
             (rawres "css/bootstrap.min.css")
             (rawres "css/fontawesome.min.css")
             (rawres "css/formatters-styles/html.css")
             (rawres "css/formatters-styles/annotated.css")
             (rawres "css/highlight-tomorrow.css")
             (rawres "img/swbydyne.png")
             (rawres "img/AGPLv3.png")
             ["resources/public/static/css/{{sanitized}}.css"
              (render "resources/public/static/css/dyne_webapp.css" data)]
             ["resources/lang/english.yaml"
              (render "resources/lang/english.yaml" data)]
             )))
