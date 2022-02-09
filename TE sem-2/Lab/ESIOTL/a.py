import RPi.GPIO as GPIO
import time
GPIO.setmode(GPIO.BCM)

IR_PIN = 14

GPIO.setup(IR_PIN, GPIO.IN)

count = 1

while True:
    got_something = GPIO.input(IR_PIN)
    if got_something:
        print("{:>3} Got something".format(count))
    else:
        print("{:>3} Nothing detected".format(count))
    count += 1
    time.sleep(0.2)


# 591 008 772
