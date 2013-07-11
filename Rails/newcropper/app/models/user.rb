class User < ActiveRecord::Base
  attr_accessor :crop_x, :crop_y, :crop_w, :crop_h
  attr_accessible :email, :name, :avatar, :crop_x, :crop_y, :crop_w, :crop_h
  has_attached_file :avatar, :styles => 
  {
  	:thumb => "100x100>",
  	:medium => "300x300>",
  	:large => "600x600>"
  }
end
