require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "RNZohoDeskPortalSalesIQ"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  s.authors      = { 'zohodesk-mobile' => 'desk-mobile@zohocorp.com' }

  s.source       = { :git => "https://github.com/zoho/react-native-zohodesk-portal-sdk.git", :tag => "#{s.version}" }
  s.source_files = "ios/RNZohoDeskPortalChat/*.{h,m,mm}"
  s.platform     = :ios, "13.0"
  s.requires_arc = true

  s.dependency "React"
  s.dependency 'ZohoDeskPortalSalesIQ', '4.0.0-beta.4' 

end
