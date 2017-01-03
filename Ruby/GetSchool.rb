require 'httparty'

#Gets the action
@action =  ARGV[0]
@id =  ARGV[1]

@url = 'http://localhost:3000'
@token = 'Token token=jStvR2Bw1mcb6jePzm7noQtt';

puts case @action
when 'Get'
	response = HTTParty.get(@url + "/v1/schools/"+@id, 
	:headers => { "Authorization" => @token})
	
when 'Post'
	@School = {school:{name: "TesteRuby"}}
	
	response = HTTParty.post(@url + "/v1/schools", 
	:headers => { "Authorization" => @token},
	:body => @School)
when 'Put'
	@School = {school:{name: "TesteRubyUpdate"}}
	
	response = HTTParty.put(@url + "/v1/schools/" + @id, 
	:headers => { "Authorization" => @token},
	:body => @School)

when 'Delete'
	response = HTTParty.delete(@url + "/v1/schools/" + @id, 
	:headers => { "Authorization" => @token})
	
else
	puts 'Get Put Post or Delete'
end


