Pod::Spec.new do |s|
  s.name         = "RNZohodeskPortalSdk"
  s.version      = "1.0.0"
  s.summary      = "RNZohodeskPortalSdk"
  s.description  = <<-DESC
                  RNZohodeskPortalSdk
                   DESC
  s.homepage         = "https://www.zoho.com/desk/"
  s.license      = "MIT"
  s.author             = { 'Bala' => 'balasubramanian.k@zohocorp.com'}
  s.platform     = :ios, "9.0"
  s.source       = { :git => "https://github.com/author/RNZohodeskPortalSdk.git", :tag => "master" }
  s.source_files  = "*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  s.dependency "ZohoDeskPortalServices", "2.0-beta"

end

  
