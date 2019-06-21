package com.example.devapi.entities.front

import kotlin.random.Random

/**
 * Created on 6/17/2019
 * @author AlexSkvor
 * */

//TODO just for tests needs to be removed later
object FrontCarGenerator {

    private val cars = arrayOf(
            Triple("Toyota", "AE86", "https://www.google.com/imgres?imgurl=http%3A%2F%2Fgetwallpapers.com%2Fwallpaper%2Ffull%2Fe%2F6%2Fe%2F95529.jpg&imgrefurl=http%3A%2F%2Fgetwallpapers.com%2Fcollection%2Finitial-d-wallpaper-hd&docid=3ITc0hpOF38MZM&tbnid=WeaWEyS8zIsmwM%3A&vet=1&w=1920&h=1080&client=firefox-b-d&bih=966&biw=1920&ved=0ahUKEwibp-est_XiAhVNUZoKHVbYCx8QMwh-KAEwAQ&iact=c&ictx=1"),
            Triple("Nissan", "R32", "https://www.google.com/imgres?imgurl=https%3A%2F%2Fmontumotors.com%2Fmedia%2FDSC_1300.jpg&imgrefurl=https%3A%2F%2Fmontumotors.com%2Finventory%2F1992-nissan-skyline-gtr-r32-4%2F&docid=y0PfzO1Okb1pfM&tbnid=_ZvOAAYwAyc3jM%3A&vet=1&w=1843&h=1229&client=firefox-b-d&bih=966&biw=1920&ved=0ahUKEwj-2bnXuPXiAhW3wcQBHbltDD4QMwiqASg0MDQ&iact=c&ictx=1"),
            Triple("Mazda", "FD", "https://www.google.com/imgres?imgurl=https%3A%2F%2Fvignette.wikia.nocookie.net%2Fgran-turismo%2Fimages%2F8%2F8b%2FMazda_RX-7_Type_R_%2528FD%252C_J%2529_%252791.jpg%2Frevision%2Flatest%3Fcb%3D20181014152655&imgrefurl=https%3A%2F%2Fgran-turismo.fandom.com%2Fwiki%2FMazda_RX-7_Type_R_(FD%2C_J)_%252791&docid=ZwoyjOLyCbe_wM&tbnid=OpOrckbnGAPOBM%3A&vet=1&w=1620&h=1080&client=firefox-b-d&bih=966&biw=1920&ved=0ahUKEwj65sv7ufXiAhWfwcQBHVNWAtMQMwjQAShbMFs&iact=c&ictx=1")
    )

    fun getFrontCarsList(number: Int = 20): Array<FrontCar> {
        var list = arrayOf<FrontCar>()
        for (i in 1..number) {
            val r = Random.nextInt(0, 3)
            val car = FrontCar(
                    id = i.toString(),
                    imageUrl = cars[r].third,
                    tradeMark = cars[r].first,
                    model = cars[r].second,
                    color = arrayOf("Black", "White", "Yellow").random(),
                    driveUnit = "rear",
                    price = Random.nextInt(3000000, 10000000).toString(),
                    year = Random.nextInt(1986, 2001).toString(),
                    bodyType = arrayOf("Sedan", "Hatchback", "Sport car").random(),
                    steeringSide = "right",
                    mileage = Random.nextInt(0, 100000).toString()
            )
            list += car
        }
        return list
    }
}