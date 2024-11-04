//
//  RNZDPThemeAdapter.swift
//  RNZohoDeskPortalConfiguration
//
//  Created by Rajesh R on 24/10/24.
//

import Foundation
import UIKit
import ZohoDeskPortalConfiguration

class RNZDPThemeToZDPThemeAdapter: ZDPThemeProtocol {
    
    private let rnzdpTheme: RNZDPThemeProtocol

    init(rnzdpTheme: RNZDPThemeProtocol) {
        self.rnzdpTheme = rnzdpTheme
    }
    
    var themeColor: UIColor {
        return rnzdpTheme.themeColor
    }
    
    var primaryBackgroundColor: UIColor {
        return rnzdpTheme.primaryBackgroundColor
    }
    
    var secondaryBackgroundColor: UIColor {
        return rnzdpTheme.secondaryBackgroundColor
    }
    
    var primaryTextColor: UIColor {
        return rnzdpTheme.primaryTextColor
    }
    
    var secondaryTextColor: UIColor {
        return rnzdpTheme.secondaryTextColor
    }
    
    var placeholderTextColor: UIColor {
        return rnzdpTheme.placeholderTextColor
    }
    
    var iconColor: UIColor {
        return rnzdpTheme.iconColor
    }
    
    var borderColor: UIColor {
        return rnzdpTheme.borderColor
    }
    
    var separatorColor: UIColor {
        return rnzdpTheme.separatorColor
    }
    
    var selectedColor: UIColor {
        return rnzdpTheme.selectedColor
    }
    
    var navigationBarBackgroundColor: UIColor {
        return rnzdpTheme.navigationBarBackgroundColor
    }
    
    var navigationBarTextColor: UIColor {
        return rnzdpTheme.navigationBarTextColor
    }
    
    var navigationBarButtonColor: UIColor {
        return rnzdpTheme.navigationBarButtonColor
    }
    
    var failureColor: UIColor {
        return rnzdpTheme.failureColor
    }
}
