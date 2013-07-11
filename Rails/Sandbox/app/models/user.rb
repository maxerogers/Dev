class User < ActiveRecord::Base
   attr_accessor :name, :email
   has_attached_file :avatar
end
