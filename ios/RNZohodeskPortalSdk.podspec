Pod::Spec.new do |s|
  s.name         = "RNZohodeskPortalSdk"
  s.version      = "4.0.0-beta.4"
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
  s.dependency 'ZohoDeskPortalCore', '4.0.0-beta.4'
  s.dependency 'ZohoDeskPortalChatKit', '4.0.0-beta.4'
  s.dependency 'ZohoDeskPortalSalesIQ', '4.0.0-beta.4'   
end

  
