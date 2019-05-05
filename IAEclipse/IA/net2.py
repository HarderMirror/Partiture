from PIL import Image
from time import time
import tensorflow as tf
from keras.layers import *
#from keras.callbacks import TensorBoard
import numpy as np
import cv2
import os

imagenes = 10000
RESCALE_VALUE = 5
width = 1280//RESCALE_VALUE
height = 175//RESCALE_VALUE

numImgTest = 65

x_train = []
y_train = cv2.imread("y.jpg",0)
y_train = y_train[:imagenes,:]

numFinalNeurons = y_train.shape[1]

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
y_train = np.array(y_train)
y_train = np.dot(y_train, 1/255)


model.add(tf.keras.layers.Conv2D(1, kernel_size=kernel_size, name='conv1_1', input_shape = input_shape))
model.add(tf.keras.layers.Activation("relu"))
model.add(tf.keras.layers.Conv2D(32, kernel_size=kernel_size, name='conv1_2'))
model.add(tf.keras.layers.Activation("relu"))
model.add(tf.keras.layers.MaxPooling2D(pool_size=(2, 2)))
model.add(tf.keras.layers.Dropout(0.25))
print("Hola")
# #2
# model.add(tf.keras.layers.Conv2D(64, kernel_size=kernel_size,name='conv2_1'))
# model.add(tf.keras.layers.Activation("relu"))
# model.add(tf.keras.layers.Conv2D(64, kernel_size=kernel_size,name='conv2_2'))
# model.add(tf.keras.layers.Activation("relu"))
# model.add(tf.keras.layers.MaxPooling2D(pool_size=(2, 2)))
# model.add(tf.keras.layers.Dropout(0.25))
# # #3
# model.add(tf.keras.layers.Conv2D(128, kernel_size=kernel_size,name='conv3_1'))
# model.add(tf.keras.layers.Activation("relu"))
# model.add(tf.keras.layers.Conv2D(128, kernel_size=kernel_size,name='conv3_2'))
# model.add(tf.keras.layers.Activation("relu"))
# model.add(tf.keras.layers.MaxPooling2D(pool_size=(2, 2)))
# model.add(tf.keras.layers.Dropout(0.25))

model.add( tf.keras.layers.Flatten())
model.add(tf.keras.layers.Dropout(0.5))

model.add(tf.keras.layers.Dense(numFinalNeurons))
model.add(tf.keras.layers.Activation('softmax'))


# optimizer = tf.train.AdamOptimizer(learning_rate=0.0005)
model.compile(optimizer='adam',
              loss='categorical_crossentropy',
              metrics=['accuracy'])

# #tensorboard = TensorBoard(log_dir="logs/{}".format(time()))
x_test = cv2.imread("images/661.jpg",0)
x_test = np.array(x_test).reshape(1, height, width, 1)
x_test = np.dot(x_test, 1/255)

model.fit(x_train, y_train, epochs=5, shuffle=True, use_multiprocessing=True, verbose=True)

model.evaluate(x_train, y_train)
pred = model.predict(x_test,verbose=1)
print(pred)


# SAVE MODEL----------------------------
# serialize model to JSON
model_json = model.to_json()
with open("model.json", "w") as json_file:
    json_file.write(model_json)
# serialize weights to HDF5
model.save_weights("model.h5")
print("Saved model to disk")
# ------------------------------------------

# # LOAD MODEL----------------------------
# json_file = open('model.json', 'r')
# loaded_model_json = json_file.read()
# json_file.close()
# model = tf.keras.models.model_from_json(loaded_model_json)
# # load weights into new model
# model.load_weights("model.h5")
# print("Loaded model from disk")
 
# # # evaluate loaded model on test data
# model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])
# x_test = cv2.imread("images/{}.jpg".format(numImgTest),0)
# x_test = np.array(x_test).reshape(1, height, width, 1)
# x_test = np.dot(x_test, 1/255)
# pred = model.predict(x_test,verbose=1)[0]
# step = 23
# data = []
# dataResult = []
# numTypeNotes = 3
# for i in range(0, len(pred), step):
#   noteData = pred[i:i+step]
#   max1 = np.argmax(noteData)
#   noteData[max1] = -1
#   max2 = np.argmax(noteData)
#   if(max1 > max2):
#     data.append([max2, max1-numTypeNotes])
#   else:
#     data.append([max1, max2-numTypeNotes])
  
#   noteDataResult = y_train[numImgTest][i:i+step]
#   max1 = np.argmax(noteDataResult)
#   noteDataResult[max1] = -1
#   max2 = np.argmax(noteDataResult)
#   if(max1 > max2):
#     dataResult.append([max2, max1-numTypeNotes])
#   else:
#     dataResult.append([max1, max2-numTypeNotes])


# print(data)
# print(dataResult)
# #------------------------------------



