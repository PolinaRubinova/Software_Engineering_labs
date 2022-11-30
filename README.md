# Software_Engineering_labs
## Coulomb per kilogram to roentgen converter

Converter that can convert coulomb/kilogram [C/kg] to roentgen [R], roentgen [R] to coulomb/kilogram [C/kg], coulomb/kilogram [C/kg] to sievert [Sv] and sievert [Sv] to roentgen [R].

## How to use

To convert [C/kg] to [R]:
``` 
http://0.0.0.0:8080/convert/c_kg/r?c_kg=<your number>
```
To convert [R] to [C/kg]:
``` 
http://0.0.0.0:8080/convert/r/c_kg?r=<your number>
```
To convert [C/kg] to [Sv]:
``` 
http://0.0.0.0:8080/convert/c_kg/sv?c_kg=<your number>
```
To convert [Sv] to [R]:
``` 
http://0.0.0.0:8080/convert/sv/r?sv=<your number>
```

## How to run with docker
Clone repository:
``` console
$ git clone https://github.com/PolinaRubinova/Software_Engineering_labs.git
```
CD into app folder:
``` console
$ cd converter
```
Build docker image:
``` console
$ docker build -t CKgtoRConverter
```
Run docker image:
``` console
$ docker run -p 8080:8080 CKgtoRConverter
```

## Lisense
[Apache](./LICENSE)