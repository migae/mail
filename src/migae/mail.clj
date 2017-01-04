(ns migae.mail
  (:import [com.google.appengine.api.mail
            MailService
            MailServiceFactory
            MailService$Attachment
            MailService$Header
            MailService$Message])
  (:require [clojure.tools.logging :as log :only [trace debug info]]))

;; derived from appengine-magic

(defonce ^{:dynamic true} *mail-service* (atom nil))

(defn mail-service []
  (when (nil? @*mail-service*)
    (do ;; (prn "mail ****************")
        (reset! *mail-service* (MailServiceFactory/getMailService))))
  @*mail-service*)

(defn send!
  "args:  to, a vector of email addresses; attachments: vector"
  [from to re msg &{ :keys [cc bcc attachments]}]
  (log/trace "sending" msg to cc bcc)
  (let [sender "test@sparky-pilots.appspotmail.com"
        m (MailService$Message.)]
    (doto m
      (.setSender from)
      (.setTo to)
      (.setSubject re)
      (.setTextBody msg))
    (if (not (nil? cc)) (.setCc m cc))
    (if (not (nil? bcc)) (.setCc m bcc))
    (if (not (nil? attachments)) (.setAttachments m attachments))
    (.send (mail-service) m)))

;; MailService:

;; send(MailService.Message message)
;; Sends a mail that has been prepared in a MailService.Message.
;; void	sendToAdmins(MailService.Message message)
;; Send an email alert to all admins of an application.

;; ;; MailService.Message:
;; void setAttachments(java.util.Collection<MailService.Attachment> attachments)
;; void	setAttachments(MailService.Attachment... attachments)
;; void	setBcc(java.util.Collection<java.lang.String> bcc)
;; void	setBcc(java.lang.String... bcc)
;; void	setCc(java.util.Collection<java.lang.String> cc)
;; void	setCc(java.lang.String... cc)
;; void	setHeaders(java.util.Collection<MailService.Header> headers)
;; void	setHeaders(MailService.Header... headers)
;; void	setHtmlBody(java.lang.String htmlBody)
;; void	setReplyTo(java.lang.String replyTo)
;; void	setSender(java.lang.String sender)
;; void	setSubject(java.lang.String subject)
;; void	setTextBody(java.lang.String textBody)
;; void	setTo(java.util.Collection<java.lang.String> to)
;; void	setTo(java.lang.String... to)


;; Javax:
;;    Message msg = new MimeMessage(session);
;;     msg.setFrom(new InternetAddress("admin@example.com", "Example.com Admin"));
;;     msg.addRecipient(Message.RecipientType.TO,
;;     new InternetAddress("user@example.com", "Mr. User"));
;;     msg.setSubject("Your Example.com account has been activated");
;;     msg.setText(msgBody);
;;     Transport.send(msg);
