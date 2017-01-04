(defproject org.mobileink/migae.mail "0.1.0-SNAPSHOT"
  :description "migae mail - MobileInk Google App Engine sdk for Clojure."
  :min-lein-version "2.0.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :url "https://github.com/migae/mail"
  :aot [#".*"]
  :test-selectors {:fields :fields
                   :meta :meta}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.google.appengine/appengine-api-1.0-sdk "1.9.17" :scope "provided"]
                 [org.clojure/tools.logging "0.2.3"]
                 [log4j "1.2.17" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]]
  :profiles {:test {:dependencies [[com.google.appengine/appengine-api-stubs "1.9.17"]
                                   [com.google.appengine/appengine-testing "1.9.17"]]}})

                                   ;; [ring-zombie "1.0.1"]]}})

                 ;; [log4j "1.2.17" :exclusions [javax.mail/mail
                 ;;                              javax.jms/jms
                 ;;                              com.sun.jdmk/jmxtools
                 ;;                              com.sun.jmx/jmxri]]
                 ;; [org.slf4j/slf4j-log4j12 "1.6.6"]
                 ;; [clj-logging-config "1.9.10"]



