Pod::Spec.new do |s|
  s.name         = "RNZohodeskPortalSdk"
  s.version      = "3.0.12"
  s.summary      = "RNZohodeskPortalSdk"
  s.description  = <<-DESC
                  RNZohodeskPortalSdk
                   DESC
  s.homepage         = "https://www.zoho.com/desk/"
  s.license      = "MIT"
  s.author             = { 'Zoho Desk-Mobile' => 'desk-mobile@zohocorp.com'}
  s.platform     = :ios, "13.0"
  s.source       = { :git => "https://github.com/author/RNZohodeskPortalSdk.git", :tag => "master" }
  s.source_files  = "*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  s.dependency "ZohoDeskPortalServices", '3.0.12'

end

  
