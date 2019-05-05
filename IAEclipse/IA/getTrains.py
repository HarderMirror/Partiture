from PIL import Image
import numpy as np
imagenes = 10000
width = 70
import cv2

#import javaobj

#with open("x.dat", "rb") as fd:
  #x_train = javaobj.loads(fd.read())

#with open("y.dat", "rb") as fd:
 # y_train = javaobj.loads(fd.read())

#np.save("y.npy",y_train)

# x_train = np.zeros((imagenes, width,175))
x_train = []
y_train = cv2.imread("y.jpg",0)
# pix_y = im_y.load()
for i in range(imagenes):
  im_x = cv2.imread('images/{}.jpg'.format(i),0)
  # pix_x = im_x.load()
  # print(i)
  # for x in range(width):
  #   for y in range(175):
  #     x_train[i,x,y] = pix_x[x,y]/255.0

  x_train.append(im_x)
                    
  # for j in range(23):
  #   y_train[i,j] =  pix_y[i,j]/255.0
print(x_train)
np.save("x.npy",x_train)
np.save("y.npy",y_train)
"""
np.save("IA/x_train.npy",x_train)
np.save("IA/y_train.npy",y_train)
"""