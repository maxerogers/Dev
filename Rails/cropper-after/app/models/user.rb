class User < ActiveRecord::Base
  attr_accessible :name, :email, :avatar
  attr_accessor :crop_x, :crop_y, :crop_w, :crop_h
  has_attached_file :avatar, :styles => 
  {
  	:thumb => "100x100#",
  	:small => "100x100>",
  	:large => "600x600>"
  },:processors => [:cropper]

  after_update :reprocess_avatar, :if => :cropping?
  
  def cropping?
    !crop_x.blank? && !crop_y.blank? && !crop_w.blank? && !crop_h.blank?
  end

  def avatar_geometry(style = :original)
    @geometry ||= {}
    @geometry[style] ||= Paperclip::Geometry.from_file(avatar.path(style))
  end
  
  private
  
  def reprocess_avatar
    avatar.reprocess!
  end
end
