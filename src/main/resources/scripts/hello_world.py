import sys

def main(argument):
    my_list = ["Hello World", argument]
    print(my_list)

if __name__ == '__main__':
    argument = "my_argument"  # Replace this with the argument you want to pass
    main(argument)