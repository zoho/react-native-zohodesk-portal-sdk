require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "RNZohoDeskPortalChatKit"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  s.authors      = { 'zohodesk-mobile' => 'desk-mobile@zohocorp.com' }

  s.platform    = :ios, "13.0"
  s.source       = { :git => "https://github.com/zoho/react-native-zohodesk-portal-sdk.git", :tag => "#{s.version}" }
  s.requires_arc = true
  s.source_files = "ios/RNZohoDeskPortalLiveChat/*.{h,m,mm,swift}"

  s.dependency "React"
  s.dependency 'ZohoDeskPortalChatKit', '4.2.0'
end
