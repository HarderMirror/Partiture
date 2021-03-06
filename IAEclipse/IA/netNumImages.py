from PIL import Image
from time import time
import tensorflow as tf
from keras.layers import *
#from keras.callbacks import TensorBoard
import numpy as np
import cv2
import os

numImage = 21
imagenes = 1000
RESCALE_VALUE = 5
width = 1280//RESCALE_VALUE
height = 175//RESCALE_VALUE
numFinalNeurons = 6

os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'


x_train = []
y_train = cv2.imread("yNumImages.jpg",0)
y_train = y_train[:imagenes,:numFinalNeurons]
for i in range(imagenes):
  im_x = cv2.imread('images/{}.jpg'.format(i),0)

  x_train.append(im_x)

# redonda,blanca,negra,pos0,pos1....redonda,blanca,negra,pos0,pos1
#100000000010000000

model = tf.keras.Sequential()

k_size_num = 5
kernel_size = (k_size_num, k_size_num)
input_shape = (height,width,1)
x_train = np.array(x_train).reshape(imagenes, height, width, 1)


x_train = np.dot(x_train, 1/255)
x_train = np.around(x_train)
x_train = x_train.astype(int)

y_train = np.array(y_train)
y_train = np.dot(y_train, 1/255)
y_train = np.around(y_train)
y_train = y_train.astype(int)

# model.add(tf.keras.layers.Conv2D(32, kernel_size=kernel_size, name='conv1_1', input_shape = input_shape))
# model.add(tf.keras.layers.LeakyReLU())
# model.add(tf.keras.layers.Conv2D(64, kernel_size=kernel_size, name='conv1_2'))
# model.add(tf.keras.layers.LeakyReLU())
# model.add(tf.keras.layers.MaxPooling2D(pool_size=(2, 2)))
# model.add(tf.keras.layers.Dropout(0.25))
# print("Hola")
# #2
# # model.add(tf.keras.layers.Conv2D(128, kernel_size=kernel_size,name='conv2_1'))
# # model.add(tf.keras.layers.Activation("relu"))
# # # model.add(tf.keras.layers.Conv2D(64, kernel_size=kernel_size,name='conv2_2'))
# # # model.add(tf.keras.layers.Activation("relu"))
# # model.add(tf.keras.layers.MaxPooling2D(pool_size=(2, 2)))
# # model.add(tf.keras.layers.Dropout(0.25))
# # #3
# # model.add(tf.keras.layers.Conv2D(128, kernel_size=kernel_size,name='conv3_1'))
# # model.add(tf.keras.layers.Activation("relu"))
# # model.add(tf.keras.layers.Conv2D(128, kernel_size=kernel_size,name='conv3_2'))
# # model.add(tf.keras.layers.Activation("relu"))
# # model.add(tf.keras.layers.MaxPooling2D(pool_size=(2, 2)))
# # model.add(tf.keras.layers.Dropout(0.25))

# model.add( tf.keras.layers.Flatten())
# model.add(tf.keras.layers.Dropout(0.5))

# model.add(tf.keras.layers.Dense(numFinalNeurons))
# model.add(tf.keras.layers.Activation("softmax"))


# # optimizer = tf.train.AdamOptimizer(learning_rate=0.001)
# model.compile(optimizer='sgd',
#               loss='categorical_crossentropy',
#               metrics=['accuracy'])

# # #tensorboard = TensorBoard(log_dir="logs/{}".format(time()))
# numImage = 0
# x_test = cv2.imread("images/{}.jpg".format(numImage),0)
# x_test = np.array(x_test).reshape(1, height, width, 1)
# x_test = np.dot(x_test, 1/255)

# model.fit(x=x_train, y=y_train, batch_size=32, epochs=20, shuffle=True, use_multiprocessing=True, verbose=True)

# model.evaluate(x_train, y_train)
# pred = model.predict(x_test,batch_size=32,verbose=1)
# print(pred)


# # SAVE MODEL----------------------------
# # serialize model to JSON
# model_json = model.to_json()
# with open("modelNumImages.json", "w") as json_file:
#     json_file.write(model_json)
# # serialize weights to HDF5
# model.save_weights("modelNumImages.h5")
# print("Saved model to disk")
# ------------------------------------------

# LOAD MODEL----------------------------
json_file = open('modelNumImages.json', 'r')
loaded_model_json = json_file.read()
json_file.close()
model = tf.keras.models.model_from_json(loaded_model_json)
# load weights into new model
model.load_weights("modelNumImages.h5")
print("Loaded model from disk")
 
# # evaluate loaded model on test data
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])
# model.evaluate(x_train, y_train)

x_test = cv2.imread("images/{}.jpg".format(numImage),0)
x_test = np.array(x_test).reshape(1, height, width, 1)
x_test = np.dot(x_test, 1/255)
x_test = np.around(x_test)
x_test = x_test.astype(int)
pred = model.predict(x_test,verbose=1)[0]
print(pred)
for i in range(len(pred)):
  if(pred[i] > 0.01):
    pred[i] = 1
  else:
    pred[i] = 0
print(pred)
out = pred.dot(2**np.arange(pred.size)[::-1])
print(out)
#------------------------------------



