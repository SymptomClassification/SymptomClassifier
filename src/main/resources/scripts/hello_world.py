import sys
import urllib.request
import json

def main(argument):
    url_chapters = 'http://dagere.comiles.eu:8090/chapters'

    with urllib.request.urlopen(url_chapters) as response:
        if response.status == 200:
            json_data = response.read().decode('utf-8')
            chapters = json.loads(json_data)
    my_list = ["Hello World", argument]
    print(chapters)

if __name__ == '__main__':
    argument = sys.argv[1:] # Replace this with the argument you want to pass
    main(argument)