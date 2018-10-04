import { Component, OnInit} from '@angular/core';
import { Router } from "@angular/router";
import { ProductService } from '../product/product.service';
import {Product} from '../product/product.model';

@Component({
    selector: 'product-list-component',
    templateUrl: './basket.component.html'

})

/**
 * @component
 * @description
 * This component will show all the added products and its detail.
 */
export class BasketComponent implements OnInit {

    /**
     * Constructor for BasketComponent class
     * @param router
     * @param _productService
     */
    constructor(private router: Router, private _productService: ProductService){
    }

    /**
     * @type {Product[]}
     */
    myBasket: Product[];

    /**
     * @type {Number}
     */
    quantity:Number = 0;

    /**
     * @type {Number}
     */
    totalPrice:Number = 0;

    /**
    * @override OnInit
     */
    ngOnInit(){
        // TODO26
        this.updateBasket();
    }

    /**
     * @name onRemoveBasketItem remove item from myBasket and update basket
     * @param item
     */
    onRemoveBasketItem = function(item){
        //TODO basket and  update basket;

    /**
     * @name onBackToMart navigate to home screen
     */
    onBackToMart = function() {
        // navigate to home screen
        // TODO30
    }

    /**
     * @name onCheckout alert for payment
     */
    onCheckout = function() {
        // this.router.navigate(['/', '']);
        // TODO28
    }

    /**
     * @name onClearCart clear all items from the cart
     */
    onClearCart = function() {
        this.myBasket = [];
        //TODO31 reset basket
        this.updateBasket();
    };

    /**
     * @name updateBasket update quantity and total price
     */
    updateBasket = function() {
        //TODO27 get quantity and total price
    }

}