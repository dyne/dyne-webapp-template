;; {{name}} - {{description}}

;; Copyright (C) {{year}}- Dyne.org foundation

;; Sourcecode designed, written and maintained by
;; TODO:

;; This program is free software; you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

;; This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.

;; You should have received a copy of the GNU Affero General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.

;; Additional permission under GNU AGPL version 3 section 7.

;; If you modify this Program, or any covered work, by linking or combining it with any library (or a modified version of that library), containing parts covered by the terms of EPL v 1.0, the licensors of this Program grant you additional permission to convey the resulting work. Your modified version must prominently offer all users interacting with it remotely through a computer network (if your version supports such interaction) an opportunity to receive the Corresponding Source of your version by providing access to the Corresponding Source from a network server at no charge, through some standard or customary means of facilitating copying of software. Corresponding Source for a non-source form of such a combination shall include the source code for the parts of the libraries (dependencies) covered by the terms of EPL v 1.0 used as well as that of the covered work.

(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "Basic compojure based authenticated website"
  :url "http://dyne.org"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 ;; ring routing
                 [compojure "1.6.1"]
                 
                 ;; HTTP server abstraction
                 [ring/ring-core "1.7.1"]
                 ;; ring middleware and defaults
                 [ring/ring-defaults "0.3.2"]
                 [ring-middleware-accept "2.0.3"]

                 ;; json
                 [cheshire "5.8.1"]

                 ;; csv
                 [org.clojure/data.csv "0.1.4"]

                 ;; mustache templates
                 [de.ubercode.clostache/clostache "1.4.0"]

                 ;; error handling
                 [failjure "1.3.0"]

                 ;; logging done right with timbre
                 [com.taoensso/timbre "4.10.0"]

                 ;; authentication library
                 [org.clojars.dyne/just-auth "0.4.0"]

                 ;; parsing configs if any
                 [io.forward/yaml "1.0.9"]

                 ;; Data validation
                 [prismatic/schema "1.1.10"]

                 ;; Common auxiliary function such as config reader
                 [org.clojars.dyne/auxiliary "0.5.0-SNAPSHOT"]]
  :aliases {"test" "midje"}
  :source-paths ["src"]
  :resource-paths ["resources"]
  :plugins [[lein-ring "0.12.4"]]
  :ring    {:init {{name}}.ring/init
            :handler {{name}}.handler/app}
  :uberwar {:init {{name}}.ring/init
            :handler {{name}}.handler/app}
  :main {{name}}.handler
  :profiles { :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                   [ring/ring-mock "0.3.2"]
                                   [midje "1.9.6"]]
                    :plugins [[lein-midje "3.1.3"]]
                    :aot :all
                    :main {{name}}.handler}
             :uberjar {:aot  :all
                       :main {{name}}.handler}}
  )
