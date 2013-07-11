class User < ActiveRecord::Base
  has_attached_file :avatar, :styles => { :small => "100x100#", :large => "500x500>" },
  :default_url => "/public/images/missing.png",
  :url  => "/users/users/:id/:style/:basename.:extension",
  :path => ":rails_root/public/images/users/:id/:style/:basename.:extension"
end
