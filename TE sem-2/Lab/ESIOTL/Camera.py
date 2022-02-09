import picamera
from time import sleep
camera = picamera.PiCamera()
camera.resolution = (1024, 768)
camera.brightness = 60
camera.start_preview()
camera.annotate_text = '31448_Rajat'
sleep(2)
camera.capture('31448_Rajat.jpeg')
camera.start_recording('31448_Rajat_video.h264') 
sleep(2)
camera.stop_recording()
camera.stop_preview()