from PIL import Image
import numpy as np
imagenes = 4000
width = 300
import javaobj as jo

with open("IA/x.npy", "rb") as fd:
    jobj = fd.read()
print(len(jo.loads(jobj)[0]))
"""
x_train = np.zeros((imagenes,width,175))
y_train = np.zeros((imagenes,23))

im_y = Image.open("IA/y.jpg")
pix_y = im_y.load()
for i in range(imagenes):
  im_x = Image.open('IA/images/{}.jpg'.format(i))
  pix_x = im_x.load()
  for x in range(width):
    for y in range(175):
      
      x_train[i,x,y] = pix_x[x,y]/255.0
  for j in range(23):
    y_train[i,j] =  pix_y[i,j]/255.0


np.save("IA/x_train.npy",x_train)
np.save("IA/y_train.npy",y_train)
"""