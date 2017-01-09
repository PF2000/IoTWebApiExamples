import json
import requests
import pprint
url = 'http://192.168.10.123:8000/v1/schools'
headers = {'Content-type': 'application/json',"Authorization":"Token token=9ZQrhUSg3rTIPla7cyHGGAtt"}
data = {"school":{"name": "TestePython"}}
data_json = json.dumps(data)

#POST
pprint.pprint('########### POST ###########')
response = requests.post(url, data=data_json,headers=headers)
pprint.pprint(response.json())

id = response.json()['id']
pprint.pprint(id)

#GET
pprint.pprint('########### GET ###########')
response = requests.get(url + "/" + str(id),headers=headers)
pprint.pprint(response.json())

#PUT
pprint.pprint('########### PUT ###########')
data = {"school":{"name": "Badoiks"}}
data_json = json.dumps(data)

response = requests.put(url + "/" + str(id), data=data_json,headers=headers)
pprint.pprint(response.json())

#DELETE
pprint.pprint('########### DELETE ###########')
response = requests.delete(url + "/" + str(id),headers=headers)
#pprint.pprint(response.json())
