require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))
folly_compiler_flags = '-DFOLLY_NO_CONFIG -DFOLLY_MOBILE=1 -DFOLLY_USE_LIBCPP=1 -Wno-comma -Wno-shorten-64-to-32'

Pod::Spec.new do |s|
  s.name         = "RNZohoDeskPortalConfiguration"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  s.authors      = { 'zohodesk-mobile' => 'desk-mobile@zohocorp.com' }

  s.platform    = :ios, "13.0"
  s.source       = { :git => "https://github.com/zoho/react-native-zohodesk-portal-sdk.git", :tag => "#{s.version}" }
  s.requires_arc = true
  s.source_files = "ios/RNZohoDeskPortalConfiguration/*.{h,m,swift}"

  s.dependency "React"
  s.dependency "ZohoDeskPortalConfiguration", '4.0.5'
  
end
