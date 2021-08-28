package com.quyenln.qmeal.broadcast

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import com.quyenln.qmeal.R
import com.quyenln.qmeal.data.repository.IMealRepository
import com.quyenln.qmeal.ui.MainActivity
import com.quyenln.qmeal.ui.detail.dish.DishDetailFragmentArgs
import com.quyenln.qmeal.util.Constant.NOTIFY_CHANNEL_ID
import com.quyenln.qmeal.util.Constant.NOTIFY_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SuggestMealBroadCast : BroadcastReceiver() {

    @Inject
    lateinit var mealRepo: IMealRepository

    override fun onReceive(context: Context?, intent: Intent?) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = mealRepo.getSingleRandomMeal()
            val meal = response.meals!![0]
            val futureTarget: FutureTarget<Bitmap> = Glide.with(context!!)
                .asBitmap()
                .load(meal.image)
                .submit(200, 200)
            val bitmap = futureTarget.get()

            val pendingIntent = NavDeepLinkBuilder(context)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.dishDetailFragment)
                .setArguments(DishDetailFragmentArgs(meal.id).toBundle())
                .createPendingIntent()

            val builder = createNotification(
                context,
                bitmap,
                pendingIntent,
                meal.name,
                meal.title
            )
            with(NotificationManagerCompat.from(context)) {
                notify(NOTIFY_ID, builder.build())
            }
        }
    }

    private fun createNotification(
        context: Context,
        bitmap: Bitmap,
        pendingIntent: PendingIntent,
        mealName: String,
        title: String
    ) = NotificationCompat.Builder(context, NOTIFY_CHANNEL_ID)
        .setSmallIcon(R.drawable.pizza)
        .setLargeIcon(bitmap)
        .setContentTitle(mealName)
        .setContentText(title)
        .setContentIntent(pendingIntent)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true)
}

