class User < ActiveRecord::Base
  attr_accessible :email, :name, :avatar,:crop_x, :crop_y, :crop_w, :crop_h
  attr_accessor :crop_x, :crop_y, :crop_w, :crop_h
  has_attached_file :avatar, :styles =>
  {
  	:large => "600x600>",
  	:medium => "300x300>",
  	:thumb => "100x100>"
  }
  has_attached_file :attachment
  after_update :reprocess_avatar, :if => :cropping?
  
  def cropping?
    !crop_x.blank? && !crop_y.blank? && !crop_w.blank? && !crop_h.blank?
  end
  
  def avatar_geometry(style = :original)
    @geometry ||= {}
    @geometry[style] ||= Paperclip::Geometry.from_file(avatar.path(style))
  end

  def reprocess_avatar
    avatar.reprocess!
  end
end
