import requests
API_URL = "http://springboot-app:8080/graphql"
def gql(query, variables=None):
    r = requests.post(API_URL, json={'query': query, 'variables': variables})
    r.raise_for_status()
    return r.json().get('data', {})
