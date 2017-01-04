(ns migae.mail-test
  (:import [com.google.appengine.tools.development.testing
            LocalServiceTestHelper
            LocalServiceTestConfig
            LocalMailServiceTestConfig])
  (:require [clojure.test :refer :all]
            [migae.mail :as mail]
            [clojure.tools.logging :as log :only [trace debug info]]))

(defn- ds-fixture
  [test-fn]
  (let [;; environment (ApiProxy/getCurrentEnvironment)
        ;; delegate (ApiProxy/getDelegate)
        helper (LocalServiceTestHelper.
                (into-array LocalServiceTestConfig
                            [(LocalMailServiceTestConfig.)]))]
    (do
        (.setUp helper)
        (mail/mail-service)
        (test-fn)
        (.tearDown helper))))
        ;; (ApiProxy/setEnvironmentForCurrentThread environment)
        ;; (ApiProxy/setDelegate delegate))))

;(use-fixtures :once (fn [test-fn] (dss/get-datastore-service) (test-fn)))
(use-fixtures :each ds-fixture)

(deftest ^:mail mail
  (testing "GAE mail service"
    (mail/send! "me@ex.org"                 ; from (sender)
                ["foo@ex.org" "bar@ex.org"] ; to
                "sparky mail test"          ; subject
                "this is a test"            ; msg
                )))

