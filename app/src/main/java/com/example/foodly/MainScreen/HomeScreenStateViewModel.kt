package com.example.foodly.MainScreen

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.example.foodly.HomeSideBars.loadCartData
import com.example.foodly.HomeSideBars.loadNotificationData
import com.example.foodly.HomeSideBars.loadProfileBarItemsData
import com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens.AddressDetail
import com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens.loadActiveOrderData
import com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens.loadAddressData
import com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens.loadCancelledOrdersData
import com.example.foodly.MainScreen.ProfileSideBarScreens.MyOrders.ProfileSideBarScreens.loadCompletedOrdersData
import com.example.foodly.R

class HomeScreenStateViewModel() : ViewModel() {

    private val _searchTextFieldState = mutableStateOf("")
    val searchTextFieldState = _searchTextFieldState

    private val _timeText = mutableStateOf("")
    val timeText = _timeText

    private val _timeTextSlogan = mutableStateOf("")
    val timeTextSlogan = _timeTextSlogan

    //Show Side bar States
    private val _showProfileSideBar = mutableStateOf(false)
    val showProfileSideBar = _showProfileSideBar

    private val _showNotificationsBar = mutableStateOf(false)
    val showNotificationsBar = _showNotificationsBar

    private val _showCartSideBar = mutableStateOf(false)
    val showCartSideBar = _showCartSideBar


    private val _recommendedText = mutableStateOf("Recommended")
    val recommendedText = _recommendedText

    private val _searchText = mutableStateOf("Search")
    val searchText = _searchText

    private val _bestSellerText = mutableStateOf("Best Seller")
    val bestSellerText = _bestSellerText

    private val _viewAllText = mutableStateOf("View All")
    val viewAllText = _viewAllText

    private val _experienceNewDishText = mutableStateOf(
        "Experience Our\n" +
                "delicious new dish"
    )
    val experienceNewDishText = _experienceNewDishText


    private val _isCartEmpty = mutableStateOf(false)
    val isCartEmpty = _isCartEmpty

    private val _noOfCartItems = mutableIntStateOf(0)
    val noOfCartItems = _noOfCartItems

    private val _subtotalAmount = mutableIntStateOf(0)
    val subtotalAmount = _subtotalAmount

    private val _taxAndFees = mutableIntStateOf(0)
    val taxAndFees = _taxAndFees

    private val _deliveryFee = mutableIntStateOf(0)
    val deliveryFee = _deliveryFee

    private val _total = mutableIntStateOf(0)
    val total = _total

    private val _myOrdersText = mutableStateOf("My Orders")
    var myOrdersText = _myOrdersText

    private val _activeButtonClicked = mutableStateOf(true)
    var activeButtonClicked = _activeButtonClicked

    private val _completedButtonClicked = mutableStateOf(false)
    var completedButtonClicked = _completedButtonClicked

    private val _cancelledButtonClicked = mutableStateOf(false)
    var cancelledButtonClicked = _cancelledButtonClicked

    private val _cancelOrderButtonText = mutableStateOf("Cancel Order")
    val cancelOrderButtonText = _cancelOrderButtonText

    private val _trackDriverButtonText = mutableStateOf("Track Driver")
    val trackDriverButtonText = _trackDriverButtonText


    private val _menuItems = loadMenuItemsData().toMutableStateList()
    val menuItems = _menuItems


    private val _bestSellerItems = loadBestSellerData().toMutableStateList()
    val bestSellerItem = _bestSellerItems


    private val _cartItemsData = loadCartData().toMutableStateList()
    val cartItemsData = _cartItemsData


    private val _notificationsData = loadNotificationData().toMutableStateList()
    val notificationsData = _notificationsData

    private val _profileSideBarData = loadProfileBarItemsData().toMutableStateList()
    val profileSideBarData = _profileSideBarData

    private val _activeOrdersData = loadActiveOrderData().toMutableStateList()
    val activeOrderData = _activeOrdersData

    private val _completedOrdersData = loadCompletedOrdersData().toMutableStateList()
    val completedOrderData = _completedOrdersData

    private val _cancelledOrdersData = loadCancelledOrdersData().toMutableStateList()
    val cancelledOrderData = _cancelledOrdersData

    private val _addressData = loadAddressData().toMutableStateList()
    var addressData = _addressData

    private val _cancelOrderText = mutableStateOf("Cancel Order")
    var cancelOrderText = _cancelOrderText

    private val _cancelOrderHeadingText =
        mutableStateOf("Please select a reason for canceling your order from the options below.")
    var cancelOrderHeadingText = _cancelOrderHeadingText

    private val _isReason1Selected = mutableStateOf(false)
    var isReason1Selected = _isReason1Selected

    private val _isReason2Selected = mutableStateOf(false)
    var isReason2Selected = _isReason2Selected

    private val _isReason3Selected = mutableStateOf(false)
    var isReason3Selected = _isReason3Selected

    private val _isReason4Selected = mutableStateOf(false)
    var isReason4Selected = _isReason4Selected

    private val _isReason5Selected = mutableStateOf(false)
    var isReason5Selected = _isReason5Selected

    private var _reasonSelectedText = mutableStateOf("")
    var reasonSelectedText = _reasonSelectedText

    private val _othersText = mutableStateOf("Others")
    var othersText = _othersText

    private val _otherReasonFieldText = mutableStateOf("")
    var otherReasonFieldText = _otherReasonFieldText

    private val _otherReasonFieldHintText = mutableStateOf("Others reason...")
    var otherReasonFieldHintText = _otherReasonFieldHintText

    private val _submitButtonText = mutableStateOf("Submit")
    var submitButtonText = _submitButtonText

    private val _orderCancelledText1 = mutableStateOf("Your has been successfully")
    var orderCancelledText1 = _orderCancelledText1

    private val _orderCancelledText2 = mutableStateOf("cancelled")
    var orderCancelledText2 = _orderCancelledText2

    private val _bottomText1 = mutableStateOf("If you have any question reach directly to our")
    var bottomText1 = _bottomText1

    private val _bottomText2 = mutableStateOf("customer support")
    var bottomText2 = _bottomText2

    private val _leaveReviewButtonText = mutableStateOf("Leave a review")
    var leaveReviewButtonText = _leaveReviewButtonText

    private val _orderedDeliveredText = mutableStateOf("Order delivered")
    var orderedDeliveredText = _orderedDeliveredText

    private val _orderAgainText = mutableStateOf("Order Again")
    var orderAgainText = _orderAgainText

    private val _leaveReviewText = mutableStateOf("Leave a Review")
    var leaveReviewText = _leaveReviewText

    private val _leaveReviewScreenText1 = mutableStateOf("We'd love to know what you")
    var leaveReviewScreenText1 = _leaveReviewScreenText1

    private val _leaveReviewScreenTExt2 = mutableStateOf("think of your dish.")
    var leaveReviewScreenText2 = _leaveReviewScreenTExt2

    private val _star1 = mutableStateOf(false)
    var star1 = _star1

    private val _star2 = mutableStateOf(false)
    var star2 = _star2

    private val _star3 = mutableStateOf(false)
    var star3 = _star3

    private val _star4 = mutableStateOf(false)
    var star4 = _star4

    private val _star5 = mutableStateOf(false)
    var star5 = _star5

    private val _leaveUsCommentText = mutableStateOf("Leave us your comment!")
    var leaveUsCommentText = _leaveUsCommentText

    private val _reviewInputText = mutableStateOf("")
    var reviewInputText = _reviewInputText

    private val _writeReviewHint = mutableStateOf("Write Review...")
    var writeReviewHint = _writeReviewHint

    private val _cancelButtonText = mutableStateOf("Cancel")
    var cancelButtonText = _cancelButtonText

    private val _orderCancelledText = mutableStateOf("Order cancelled")
    var orderCancelledText = _orderCancelledText

    private val _showBottomNavigationComposable = mutableStateOf(true)
    var showBottomNavigationComposable = _showBottomNavigationComposable

    private val _myProfileText = mutableStateOf("My Profile")
    var myProfileText = _myProfileText

    private val _fullName = mutableStateOf("")
    var fullName = _fullName

    private val _email = mutableStateOf("")
    var email = _email

    private val _phoneNumber = mutableStateOf("")
    var phoneNumber = _phoneNumber

    private val _updateProfileButtonText = mutableStateOf("Update Profile")
    var updateProfileButtonText = _updateProfileButtonText


    private val _deliveryAddressText = mutableStateOf("Deliver Address")
    var deliveryAddressText = _deliveryAddressText


    private val _selectedAddress: AddressDetail = AddressDetail("","", mutableStateOf(false))
    var selectedAddress = _selectedAddress


    private val _addAddressButtonText = mutableStateOf("Add New Address")
    var addAddressButtonText  =_addAddressButtonText

    private val _addressName = mutableStateOf("")
    var addressName = _addressName

    private val _address = mutableStateOf("")
    var address = _address

    private val _applyButtonText = mutableStateOf("Apply")
    var applyButtonText = _applyButtonText

    private val _addNewAddressText = mutableStateOf("Add New Address")
    var addNewAddressText = _addNewAddressText

    //focus requester
    val focusRequester1 = FocusRequester()
    val focusRequester2 = FocusRequester()
    val focusRequester3 = FocusRequester()
}