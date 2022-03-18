# new feature
# Tags: optional

Feature: Hepsiburada Login

  Scenario: Geçerli kullanıcı adı ve şifre ile giriş
    Given Hepsiburada login sayfasına girilir
    When Kullanıcı adı olarak "cagataytok@windowslive.com" olarak girilir
    When Şifre olarak "Sac02191." girilir
    Then Kullanıcı olarak "Mesut Çağatay Tok" görülür

  Scenario: Geçerli kullanıcı adı ve geçersiz şifre ile giriş
    Given Hepsiburada login sayfasına girilir
    When Kullanıcı adı olarak "cagataytok@windowslive.com" olarak girilir
    When Şifre olarak "test1234." girilir
    Then Hata mesajı olarak "Girdiğiniz şifre eksik veya hatalı." görülür

  Scenario: Geçersiz kullanıcı adı ile giriş
    Given Hepsiburada login sayfasına girilir
    When Kullanıcı adı olarak "cagataytok1@windowslive.com" olarak girilir
    Then Hata mesajı olarak "E-posta adresi eksik veya hatalı." görülür

  Scenario: Şifremi unuttum fonksiyonun kontrolü
    Given Hepsiburada login sayfasına girilir
    When Kullanıcı adı olarak "cagataytok@windowslive.com" olarak girilir
    When Şifremi unuttum butonuna tıklanır
    Then Hesabı doğrulama alanında seçenek olarak "cagataytok@windowslive.com" alanının geldiği görülür

  Scenario: Şifremi göster fonksiyonun kontrolü
    Given Hepsiburada login sayfasına girilir
    When Kullanıcı adı olarak "cagataytok@windowslive.com" olarak girilir
    When Şifre olarak "Sac02191." girilir
    When Şifremi göster butonuna tıklanır
    Then Şifre olarak "Sac02191." geldiği girilir

  Scenario: Farklı hesap kullan fonksiyonun kontrolü
    Given Hepsiburada login sayfasına girilir
    When Kullanıcı adı olarak "cagataytok@windowslive.com" olarak girilir
    When Farklı hesap kullan butonuna tıklanır
    Then E-posta giriş alanının geldiği görülür

  Scenario: Farklı e-posta adresi ile üye olma islemi
    Given Hepsiburada login sayfasına girilir
    When Üye ol butonuna tıklanır
    When Kullanıcı adı olarak "tokcagatay@hotmail.com" olarak girilir
    Then "Doğrulama maili gönderildi" mesajı geldiği görülür

  Scenario: Kullanılan e-posta adresi ile üye olma islemi
    Given Hepsiburada login sayfasına girilir
    When Üye ol butonuna tıklanır
    When Kullanıcı adı olarak "cagataytok@windowslive.com" olarak girilir
    Then "Bu e-posta adresine ait bir hesabınız olduğunu fark ettik." mesajı geldiği görülür


