class AddAttachment < ActiveRecord::Migration
  def up
  	add_column :users, :attach_file_name, 		:string
  	add_column :users, :attach_content_type, 	:string
  	add_column :users, :attach_file_size, 		:integer
  	add_column :users, :attach_updated_at, 		:datetime
  end

  def down
  	remove_column :users, :attach_file_name
  	remove_column :users, :attach_content_type
  	remove_column :users, :attach_file_size
  	remove_column :users, :attach_updated_at
  end
end
