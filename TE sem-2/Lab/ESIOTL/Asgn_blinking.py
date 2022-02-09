import RPi.GPIO as GPIO
from time import sleep

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)
GPIO.setup(10, GPIO.OUT)

for i in range(1,10):
 GPIO.output(10, GPIO.HIGH)
 sleep(1)
 GPIO.output(10, GPIO.LOW)
 sleep(1)

